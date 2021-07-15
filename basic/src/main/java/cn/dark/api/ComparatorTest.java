package cn.dark.api;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dark
 * @date 2021-06-26
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<IntegerComparator> list = new ArrayList<>();
        list.add(new IntegerComparator(2, 3));
        list.add(new IntegerComparator(2, 1));
        list.add(new IntegerComparator(2, null));
        list.add(new IntegerComparator(3, 3));
        list.add(new IntegerComparator(4, 2));
        list.add(new IntegerComparator(null, 1));
        list.add(new IntegerComparator(2, 2));

        boolean isAsc = true;
        Comparator<Integer> keyComparator = isAsc ? Comparator.nullsLast(Integer::compareTo) : Comparator.nullsFirst(Integer::compareTo);
        Comparator<IntegerComparator> comparator = Comparator.comparing(IntegerComparator::getI, keyComparator)
                .thenComparing(IntegerComparator::getJ, keyComparator);
        if (!isAsc) {
            comparator = comparator.reversed();
        }
        List<IntegerComparator> sorted = list.stream()
                .filter(item -> Objects.nonNull(item))
                .sorted(comparator)
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(sorted.toArray()));
    }

}

class IntegerComparator {
    private Integer i;

    private Integer j;

    public IntegerComparator(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }

    public Integer getI() {
        return i;
    }

    public Integer getJ() {
        return j;
    }

    @Override
    public String toString() {
        return i + "-" + j;
    }
}
