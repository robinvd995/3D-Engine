package engine.utils;

import engine.renderer.Camera;

public class MathHelper {

	public static Matrix4f createTransformationMatrix(Vector3f position, float sx, float sy, float sz, Quaternion quat){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(position, matrix, matrix);
		Matrix4f.mul(matrix, quat.toMatrix(), matrix);
		Matrix4f.scale(new Vector3f(sx, sy, sz), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector3f position, Vector3f rot, float sx, float sy, float sz){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(position, matrix, matrix);
		Matrix4f.rotate(rot.x, GlobalAxis.X.toVector(), matrix, matrix);
		Matrix4f.rotate(rot.y, GlobalAxis.Y.toVector(), matrix, matrix);
		Matrix4f.rotate(rot.z, GlobalAxis.Z.toVector(), matrix, matrix);
		Matrix4f.scale(new Vector3f(sx, sy, sz), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1.0f), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f m = new Matrix4f();
		m.setIdentity();
		//Matrix4f.rotate(AngleHelper.toRadians(camera.getPitch()), GlobalAngle.X.toVector(), m, m);
		//Matrix4f.rotate(AngleHelper.toRadians(camera.getYaw()), GlobalAngle.Y.toVector(), m, m);
		//Matrix4f.rotate(AngleHelper.toRadians(camera.getRoll()), GlobalAngle.Z.toVector(), m, m);
		Matrix4f.mul(m, camera.getRotation().toMatrix(), m);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos, m, m);
		return m;
	}
	
	public static float clampf(float value, float min, float max){
		if(value < min){
			return min;
		}
		if(value > max){
			return max;
		}
		return value;
	}
	
	public static double clamp(double value){
		if(value < 0.0){
			return 0.0;
		}
		if(value > 1.0){
			return 1.0;
		}
		return value;
	}
	
	public static float lerp(float start, float end, float percentage){
		return (start + percentage * (end - start));
	}
	
	public static Vector3f multVector3f(Vector3f v1, float f){
		return new Vector3f(v1.x * f, v1.y * f, v1.z * f);
	}
	
	public static Vector3f add(Vector3f v1, Vector3f v2){
		return new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}
	
	public static float dist(Vector3f v1, Vector3f v2){
		float dx = v2.x - v1.x;
		float dy = v2.y - v1.y;
		float dz = v2.z = v1.z;
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	public static Vector3f worldToScreenSpace(Vector3f position, Matrix4f viewMatrix, Matrix4f proMatrix){
		Vector4f coords = new Vector4f(position.x, position.y, position.z, 1.0f);
		Matrix4f.transform(viewMatrix, coords, coords);
		Matrix4f.transform(proMatrix, coords, coords);
		Vector3f screenCoords = clipSpaceToScreenSpace(coords);
		return screenCoords;
	}
	
	private static Vector3f clipSpaceToScreenSpace(Vector4f coords){
		if(coords.w < 0){
			return null;
		}
		Vector3f screenCoords = new Vector3f(((coords.x) / coords.w) / 2f, 1 - (((coords.y / coords.w) + 1) / 2f), coords.z);
		return screenCoords;
	}
}
