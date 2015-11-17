package lightning;

public class LightningMock implements Lightning {

    public boolean turnedOnlights = false;
    public boolean turnedOffLights = false;
    public boolean turnedOnDiscoLights = false;

    public void turnOnLights() {
        turnedOnlights = true;
    }

    public void turnOnDiscoLights() {
        turnedOnDiscoLights = true;
    }

    public void turnOffLights() {
        turnedOffLights = true;
    }
}
