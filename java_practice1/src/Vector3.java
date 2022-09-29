import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Vector3 {
    private final float DEFAULT_VALUE = 1f;
    public float X;
    public float Y;
    public float Z;
    private ArrayList<Float> _data = new ArrayList<>();
    public Vector3(Float... params){
        for (int i = 0; i < 3; i++){
            if (i < params.length){
                _data.add(params[i]);
            } else {
                _data.add(DEFAULT_VALUE);
            }
        }
        X = _data.get(0);
        Y = _data.get(1);
        Z = _data.get(2);
    }

    public static Vector3[] generateVectors(int n){
        Vector3[] vector3s = new Vector3[n];
        for (int i = 0; i < n; i++){
            Float[] params = new Float[3];
            for (int j = 0; j < 3; j++){
                float random = (float)Math.random() * 100;
                params[j] = random;
            }
            vector3s[i] = new Vector3(params);
        }
        return vector3s;
    }

    public static String[] toString(Vector3[] vectors){
        int n = vectors.length;
        String[] string = new String[n];
        for (int i = 0; i < n; i++){
            string[i] = vectors[i].toString();
        }
        return string;
    }

    @Override
    public String toString(){
        return String.format("Vector3(x: %s, y: %s, z: %s)", X, Y, Z);
    }

    public float getVectorLength(){
        return (float)Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2) + Math.pow(Z, 2));
    }

    public float getDotProduct(Vector3 other) {
        return X * other.X + Y * other.Y + Z * other.Z;
    }

    public Vector3 getVectorProduct(Vector3 other){
        return new Vector3(Y * other.Z - Z * other.Y, Z * other.X - X * other.Z, X * other.Y - Y * other.X);
    }

    public float getAngleInRandiansBetweenVectors(Vector3 other){
        return getDotProduct(other) / Math.abs(getVectorLength() * other.getVectorLength());
    }

    public Vector3 addVector3(Vector3 other){
        return new Vector3(X + other.X, Y + other.Y, Z + other.Z);
    }

    public Vector3 subVector3(Vector3 other){
        return new Vector3(X - other.X, Y - other.Y, Z - other.Z);
    }
}
