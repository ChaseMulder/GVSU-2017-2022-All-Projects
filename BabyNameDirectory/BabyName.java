
public class BabyName implements Comparable {

    String nameOfBaby; // Name of baby

    boolean genderOfBaby; // true or false for female or male

    int totalBabyName, yearOfBabyName; // total number of names for a baby name, year for specific name

    public BabyName(String nob, boolean gob, int count, int yobn) {

        nameOfBaby = nob;

        genderOfBaby = gob;

        totalBabyName = count;

        yearOfBabyName = yobn;

    }

    public boolean isFemale() {

        return genderOfBaby;

    }

    public String getName() {

        return nameOfBaby;

    }

    public int getCount() {

        return totalBabyName;

    }

    public int getYear() {

        return yearOfBabyName;

    }

    public void setName(String nob) {

        this.nameOfBaby = nob;

    }

    public void setCount(int count) {

        this.totalBabyName = count;

    }

    public void setYear(int yobn) {

        this.yearOfBabyName = yobn;

    }

    public String toString() {

        String string;

        if (isFemale())

            string = totalBabyName + " girls named " + nameOfBaby + " in " + yearOfBabyName;

        else

            string = totalBabyName + " boys named " + nameOfBaby + " in " + yearOfBabyName;

        return string;

    }

    public static void main(String[] args) {

        {

            BabyName name1 = new BabyName("Jessica", true, 46473, 1990);

            System.out.println(name1.toString());

            System.out.println(name1.isFemale());

            System.out.println(name1.toString());

        }

    }

    @Override

    public int compareTo(Object other) {

        BabyName b = (BabyName) other;

        return (b.totalBabyName - this.totalBabyName);

    }

}