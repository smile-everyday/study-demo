package cn.dark.api;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lwj
 * @date 2020-11-05
 */
public class LocalDateDemo {

    public static void main(String[] args) {
        // localDate();

        // zonedDate();

        // caclArriveTime();

        System.out.println("2021-01-13".compareTo("2021-02"));
    }

    private static void caclArriveTime() {
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    private static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        ZonedDateTime zonedDateTime = bj.atZone(ZoneId.systemDefault());
        ZonedDateTime arriveTime = zonedDateTime.plusHours(h).plusMinutes(m);
        ZonedDateTime zonedDateTime1 = arriveTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        return zonedDateTime1.toLocalDateTime();
    }

    private static void zonedDate() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("默认时区当前时间：" + now);
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("指定时区当前时间：" + now1);
        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        System.out.println("指定时区当前时间：" + now2);

        // atZone会创建不同时区相同时间的时间，但不是同一个时刻
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbj = ldt.atZone(ZoneId.systemDefault());
        System.out.println("北京指定时间：" + zbj);
        ZonedDateTime zny = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println("纽约指定时间：" + zny);

        ZonedDateTime changeZone = now.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("转换时区：" + changeZone);

        LocalDateTime changeLocalDate = now1.toLocalDateTime();
        System.out.println("转换本地时间（时刻不会变，只是把时区丢掉了）：" + changeLocalDate);
    }

    private static void localDate() {
        LocalDate now = LocalDate.now();
        System.out.println("当月天数：" + now.lengthOfMonth());
        LocalDate localDate = now.withDayOfMonth(1);
        System.out.println("当月一号：" + localDate);

        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println("当日的开始时间：" + localDateTime);
        System.out.println("格式化当日的开始时间：" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        OffsetDateTime offsetDateTime = localDate.atTime(OffsetTime.MAX);
        System.out.println("当日结束时间：" + offsetDateTime);
        System.out.println("格式化当日结束时间：" + offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        OffsetDateTime offsetDateTime1 = localDate.atTime(OffsetTime.MIN);
        System.out.println("当日的开始时间：" + offsetDateTime1);
        System.out.println("格式化当日的开始时间：" + offsetDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        OffsetDateTime offsetDateTime2 = offsetDateTime.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月最后一天：" + offsetDateTime2);

        LocalDate localDate1 = now.minusMonths(1);
        System.out.println("上一个月：" + localDate1);
        System.out.println("上月1号：" + localDate1.withDayOfMonth(1));
        System.out.println("上月最后一天：" + localDate1.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDate localDate2 = now.minusWeeks(1);
        System.out.println("上周：" + localDate2);
        System.out.println("上周一：" + localDate2.with(DayOfWeek.MONDAY));
        System.out.println("上周日：" + localDate2.with(DayOfWeek.SUNDAY));

        LocalDate parse = LocalDate.parse("2020-10-11");
        System.out.println("解析字符串为时间：" + parse);
    }

}
