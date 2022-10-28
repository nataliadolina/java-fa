package Figures;

public class Box extends Shape{
    public Box(double volume){
        _volume = volume;
    }
    public boolean add(double volume){
        if (_volume - volume < 0){
            return false;
        }
        _volume -= volume;
        return true;
    }

}
