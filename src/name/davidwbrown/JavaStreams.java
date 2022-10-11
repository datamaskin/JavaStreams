package name.davidwbrown;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStreams {

    static class binGap {
        String _binary;
        int count = 0;
        public int result(int N) {

            char temp;

            int count_ = 0;
            if (N < 0)
                return 0;
            else {
                _binary = Integer.toBinaryString(N);
            }
            if (_binary.isBlank()) return 0;
            if (!_binary.contains("1")) return 0;
            for (int i = 0; i < _binary.length(); i++) {
                temp = _binary.charAt(i);
                if (temp == '1') count++;
            }
            if (count < 2) return 0;
            char binary_[] = _binary.toCharArray();
            count = 0;
            for (int i = 0; i < _binary.length(); i++) {
                if (binary_[i]=='0') count++;
                else if (binary_[i]=='1') {
                    if (count > count_) {
                        count_ = count;
                        count = 0;
                    }
                    else count = 0;
                }
            }
            return count_;
        }
    }

    static class ParkingBill {
        public int getBill(String E, String L) {
            int fee = 0;
            if (E.contains("-")) return fee;
            if (L.contains("-")) return fee;

            String pattern = "([0-9][0-9]:[0-9][0-9])";

            Pattern p = Pattern.compile(pattern);

            Matcher m = p.matcher(E);
            Matcher _m = p.matcher(L);

            if (m.find() && _m.find()) {
                fee = 2;
            } else {
                return 0;
            }

            String[] eHrsMin = E.split(":");
            String[] lHrsMin = L.split(":");

            int eHrs = Integer.parseInt(eHrsMin[0]);
            int eMin = Integer.parseInt(eHrsMin[1]);
            int lHrs = Integer.parseInt(lHrsMin[0]);
            int lMin = Integer.parseInt(lHrsMin[1]);

            if (eHrs < 0 || eHrs > 23) return 0;
            if (lHrs < 0 || lHrs > 23) return 0;
            if (lMin < 0 || lMin > 59) return 0;
            if (eMin < 0 || eMin > 59) return 0;
            if (!(lHrs-eHrs >= 0)) return 0;
            fee += 3;

            int hrs = lHrs-eHrs;
            int min = lMin-eMin;
            int remHrs = hrs-1;
            if (remHrs>0)
                fee += 4*remHrs;
            if (min>0) fee += 1;

            return fee;
        }
    }

    static class FirstUnique {
        public int getUnique(int[] A) {
            int result = -1;

            Set<Integer> set = new HashSet<Integer>();

            for (int i = 0; i < A.length; i++) {
                if (set.add(A[i]) == false) {
                    int tmp = A[i];
                    A[i] = -1;
                    for (int j = 0; j < A.length; j++) {
                        if (A[j] == tmp)
                            A[j] = -1;
                    }
                }
            }

            for (int i = 0; i < A.length; i++) {
                if (A[i] != -1) {
                    result = A[i];
                    break;
                } else if (i > 0) {
                    result = A[i-1];
                    break;
                } else {
                    result = A[i+1];
                    break;
                }
            }

            return result;
        }
    }

    static class SolutionIter implements Iterable<Integer> {

        Reader reader;
        CharBuffer cb = CharBuffer.allocate(100);
        String str = null;
        List<Integer> nums = new ArrayList<>();

        public SolutionIter(Reader inp) throws IOException {

            reader = inp;
            int result = reader.read(cb);
            StringBuilder sb = new StringBuilder();
            StringBuilder _sb = new StringBuilder();
            String letter = null;
            if (result != -1) {
                for (int i = 0; i < result; i++) {
                    if (Character.isDigit(cb.get(i))) {
                        sb.append(cb.get(i));
                    } else if (Character.isLetter(cb.get(i))) {
                        _sb.append(cb.get(i));
                    } else if (cb.get(i) == '-') {
                        sb.append(cb.get(i));
                    } else if (cb.get(i) == '.') {
                        sb.append(cb.get(i));
                    }
                    else if (cb.get(i) == '\n') {
                        if (sb.toString().contains(".")) {
                            str = sb.toString().replaceAll("\\s+","");
                            float f = Float.parseFloat(str);
                            nums.add(Math.round(f));
                        } else if (sb.toString().isEmpty() && !_sb.toString().isEmpty()) {
                            str = _sb.toString().replaceAll("\\s+","");
                            if (str.equalsIgnoreCase("five")) str = "5";
                            nums.add(Integer.parseInt(str));
                        } else {
                            str = sb.toString().replaceAll("\\s+","");
                            nums.add(Integer.parseInt(str));
                        }
                        sb = new StringBuilder();
                    }

                }
            }
            reader.close();
        }

        @Override
        public Iterator<Integer> iterator() {
            return nums.iterator();
        }
    }

    static class NTimesHello {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        {
            try {
                line = br.readLine();
                int N = Integer.parseInt(line);
                for (int i = 0; i < N; i++) {
                    System.out.println("hello world");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    public static void main(String[] args) throws IOException {
        /*List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        myList
                .stream()
                .filter(s -> s.contains("2"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);*/

        /*binGap bg = new binGap();

        int x = bg.result(539);
        System.out.println(x);
        System.out.println(bg._binary);*/

        /*ParkingBill pb = new ParkingBill();
        int input = pb.getBill("00:00", "24:00");
        if (input == 0) {
            System.out.println("Invalid input");
        } else {
            System.out.println("Fee: " + input);
        }*/

        /*FirstUnique f = new FirstUnique();
        int[] A = {4,10,5,4,2,10};
        int result = f.getUnique(A);
        System.out.println("First unique: " + result);*/

        /*SolutionIter solutionIter = new SolutionIter(new FileReader("file.txt"));

        for (Integer s : solutionIter.nums) {
            System.out.println(s);
        }*/
        NTimesHello nt = new NTimesHello();

    }

}
