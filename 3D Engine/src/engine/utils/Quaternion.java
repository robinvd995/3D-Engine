package engine.utils;


public class Quaternion {
	private float x, y, z, w;

	public Quaternion() {
		reset();
	}

	public Quaternion(float x, float y, float z, float w) {
		set(x, y, z, w);
	}

	public Quaternion(float angle, Vector3f vec) {
		float s = (float)Math.sin(angle / 2);

		x = vec.x * s;
		y = vec.y * s;
		z = vec.z * s;
		w = (float)Math.cos(angle / 2);
	}

	public Quaternion(Quaternion q) {
		set(q);
	}

	public Quaternion copy() {
		return new Quaternion(this);
	}

	public float x() {
		return x;
	}

	public Quaternion x(float x) {
		this.x = x;
		return this;
	}

	public float y() {
		return y;
	}

	public Quaternion y(float y) {
		this.y = y;
		return this;
	}

	public float z() {
		return z;
	}

	public Quaternion z(float z) {
		this.z = z;
		return this;
	}

	public float w() {
		return w;
	}

	public Quaternion w(float w) {
		this.w = w;
		return this;
	}

	public Quaternion set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	public Quaternion set(Quaternion q) {
		return set(q.x, q.y, q.z, q.w);
	}

	public Quaternion reset() {
		x = y = z = 0;
		w = 1;
		return this;
	}

	public float length() {
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaternion normalize() {
		float length = length();
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		return this;
	}

	public float dot(Quaternion q) {
		return x * q.x + y * q.y + z * q.z + w * q.w;
	}

	public Quaternion add(float x, float y, float z, float w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;

		return this;
	}

	public Quaternion add(Quaternion q) {
		return add(q.x, q.y, q.z, q.w);
	}

	public Quaternion sub(float x, float y, float z, float w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;

		return this;
	}

	public Quaternion sub(Quaternion q) {
		return sub(q.x, q.y, q.z, q.w);
	}

	public Quaternion mult(float f) {
		this.x *= f;
		this.y *= f;
		this.z *= f;
		this.w *= f;

		return this;
	}

	public Vector3f mult(Vector3f v) {
		Vector3f quatVector = new Vector3f(x, y, z);

		Vector3f uv = new Vector3f(quatVector.y * v.z - v.y * quatVector.z, quatVector.z * v.x - v.z * quatVector.x, quatVector.x * v.y - v.x * quatVector.y);
		Vector3f uuv = new Vector3f(quatVector.y * uv.z - uv.y * quatVector.z, quatVector.z * uv.x - uv.z * quatVector.x, quatVector.x * uv.y - uv.x * quatVector.y);

		uv.x *= w * 2;
		uv.y *= w * 2;
		uv.z *= w * 2;

		uuv.x *= 2;
		uuv.y *= 2;
		uuv.z *= 2;

		return new Vector3f(v.x + uv.x + uuv.x, v.y + uv.y + uuv.y, v.z + uv.z + uuv.z);
	}

	public Quaternion mult(Quaternion q) {
		float xx = w * q.x + x * q.w + y * q.z - z * q.y;
		float yy = w * q.y + y * q.w + z * q.x - x * q.z;
		float zz = w * q.z + z * q.w + x * q.y - y * q.x;
		float ww = w * q.w - x * q.x - y * q.y - z * q.z;

		x = xx;
		y = yy;
		z = zz;
		w = ww;

		return this;
	}

	public Quaternion conjugate() {
		x *= -1;
		y *= -1;
		z *= -1;

		return this;
	}

	public Quaternion inverse() {
		return normalize().conjugate();
	}

	public Matrix4f toMatrix() {
		return toMatrix(null);
	}

	public Matrix4f toMatrix(Matrix4f mat4) {
		if(mat4 == null)
			mat4 = new Matrix4f();

		/*mat4.set(new float[] {
				1 - 2 * y * y - 2 * z * z, 2 * x * y + 2 * w * z, 2 * x * z - 2 * w * y, 0,
				2 * x * y - 2 * w * z, 1 - 2 * x * x - 2 * z * z, 2 * y * z + 2 * w * x, 0,
				2 * x * z + 2 * w * y, 2 * y * z - 2 * w * x, 1 - 2 * x * x - 2 * y * y, 0,
				0, 0, 0, 1,
		});*/
		mat4.m00 = 1 - 2 * y * y - 2 * z * z;
		mat4.m01 = 2 * x * y + 2 * w * z;
		mat4.m02 = 2 * x * z - 2 * w * y;
		mat4.m03 = 0;

		mat4.m10 = 2 * x * y - 2 * w * z;
		mat4.m11 = 1 - 2 * x * x - 2 * z * z;
		mat4.m12 = 2 * y * z + 2 * w * x;
		mat4.m13 = 0;

		mat4.m20 = 2 * x * z + 2 * w * y;
		mat4.m21 = 2 * y * z - 2 * w * x;
		mat4.m22 = 1 - 2 * x * x - 2 * y * y;
		mat4.m23 = 0;

		mat4.m30 = 0;
		mat4.m31 = 0;
		mat4.m32 = 0;
		mat4.m33 = 1;

		return mat4;
	}

	public static Quaternion fromVector(Vector3f vec){
		Quaternion q = new Quaternion();
		q.mult(new Quaternion((float)Math.toRadians(vec.x), GlobalAxis.X.toVector()));
		q.mult(new Quaternion((float)Math.toRadians(vec.y), GlobalAxis.Y.toVector()));
		q.mult(new Quaternion((float)Math.toRadians(vec.z), GlobalAxis.Z.toVector()));
		return q;
	}

	public static Quaternion slerp(float amount, Quaternion value1, Quaternion value2){
		if ((value1 == null) || (value2 == null)){
			return null;
		}

		if (amount < 0.0)
			return value1;
		else if (amount > 1.0)
			return value2;

		float dot = value1.dot(value2);
		float x2, y2, z2, w2;
		if (dot < 0.0){
			dot = 0.0f - dot;
			x2 = 0.0f - value2.x;
			y2 = 0.0f - value2.y;
			z2 = 0.0f - value2.z;
			w2 = 0.0f - value2.w;
		}
		else{
			x2 = value2.x;
			y2 = value2.y;
			z2 = value2.z;
			w2 = value2.w;
		}

		float t1, t2;

		final float EPSILON = 0.0001f;
		if ((1.0 - dot) > EPSILON){
			float angle = (float) Math.acos(dot);
			float sinAngle = (float) Math.sin(angle);
			t1 = (float) (Math.sin((1.0 - amount) * angle) / sinAngle);
			t2 = (float) (Math.sin(amount * angle) / sinAngle);
		}
		else{
			t1 = 1.0f - amount;
			t2 = amount;
		}

		return new Quaternion(
				(value1.x * t1) + (x2 * t2),
				(value1.y * t1) + (y2 * t2),
				(value1.z * t1) + (z2 * t2),
				(value1.w * t1) + (w2 * t2));
	}
}
