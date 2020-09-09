package cn.dark.observer;

/**
 * @author dark
 * @date 2018-11-17
 */
public class WeateherData implements Subject {

    private double temperature;
    private double pressure;
    private double humidity;

    public double getTemperature() {
        return this.temperature;
    }
    public double getPressure() {
        return this.pressure;
    }
    public double getHumidity() {
        return this.humidity;
    }

    public void changeData(double temperature, double pressure, double humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;

        // 主题决定需要推送哪些数据
        notifyAllObservers(this);

        // 观察者自己拉取需要的数据
        // notifyAllObservers();
    }
}
