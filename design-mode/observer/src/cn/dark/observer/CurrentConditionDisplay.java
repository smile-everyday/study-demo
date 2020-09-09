package cn.dark.observer;

/**
 * @author dark
 * @date 2018-11-17
 */
public class CurrentConditionDisplay implements Observer {
    private double temperature;
    private double pressure;
    private double humidity;

    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        subject.register(this);
    }

    @Override
    public void update(Subject subject, Object arg) {
        // 观察者被动接收主题传送的数据
        if (arg instanceof WeateherData) {
            WeateherData weateherData = (WeateherData) arg;
            this.temperature = weateherData.getTemperature();
            this.pressure = weateherData.getPressure();
            this.humidity = weateherData.getHumidity();

            display();
        }

        // 观察者自己拉取所需数据
        // if (subject instanceof WeateherData) {
        //     WeateherData weateherData = (WeateherData) subject;
        //     this.temperature = weateherData.getTemperature();
        //     this.pressure = weateherData.getPressure();
        //     this.humidity = weateherData.getHumidity();
        //
        //     display();
        // }
    }

    private void display() {

        System.out.println("temperature: " + temperature + "pressure: " + pressure + "humidity: " + humidity);

    }

}
