import java.util.ArrayList;

public class Main {
  public static void main(String argv[]) {
    testOne();
    testTwo();
  }

  //--------------------------------------------------------
  // example of how to set up a testcase
  //
  // Frank    GEOG010  GEOG011
  // Cynthia  GEOG010
  // Walter                     GEOG101
  // George                     GEOG101  GEOG201
  //
  // here is the solution (size = 2):
  // Frank: GEOG010 GEOG011
  // George: GEOG101 GEOG201

  public static void testOne() {
    Instructor frank = new Instructor("Frank");
    String frankCourses[] = {"GEOG010", "GEOG011"};
    frank.teach(frankCourses);

    Instructor cynthia = new Instructor("Cynthia");
    String cynthiaCourses[] = {"GEOG010"};
    cynthia.teach(cynthiaCourses);

    Instructor walter = new Instructor("Walter");
    String walterCourses[] = {"GEOG101"};
    walter.teach(walterCourses);

    Instructor george = new Instructor("George");
    String georgeCourses[] = {"GEOG101", "GEOG201"};
    george.teach(georgeCourses);

    Instructor[] instructors = {frank, cynthia, walter, george};
    String[] courses = {"GEOG010", "GEOG011", "GEOG101", "GEOG201"};

    Instructor[] coverSet = findMinCover(courses, instructors);
    for (int i = 0; i < coverSet.length; ++i)
      System.out.println(coverSet[i]);
  }

  //-----------------------------------------------------------------------
  // Write this testcase to mode the example from the assignment spec
  //
  // here's the unique solution (of size = 4):
  // John:  ART002 ART008 ART125
  // Betsy: ART124 ART125 ART201
  // Hiram: ART001 ART110 ART125
  // Ralph: ART008 ART064 ART205 ART266

  public static void testTwo() {
    // fill this in
    Instructor john = new Instructor("John");
    String johnCourses[] = {"ART002" , "ART008", "ART125"};
    john.teach(johnCourses);

    Instructor tom = new Instructor("Tom");
    String tomCourses[] = {"ART008" , "ART110", "ART125"};
    tom.teach(tomCourses);

    Instructor mary = new Instructor("Mary");
    String maryCourses[] = {"ART008" , "ART266"};
    mary.teach(maryCourses);

    Instructor alicia = new Instructor("Alicia");
    String aliciaCourses[] = {"ART064" , "ART110", "ART125", "ART266"};
    alicia.teach(aliciaCourses);

    Instructor betsy = new Instructor("Betsy");
    String betsyCourses[] = {"ART124" , "ART125", "ART201"};
    betsy.teach(betsyCourses);

    Instructor kira = new Instructor("Kira");
    String kiraCourses[] = {"ART008" , "ART266"};
    kira.teach(kiraCourses);

    Instructor hiram = new Instructor("Hiram");
    String hiramCourses[] = {"ART001", "ART110", "ART125"};
    hiram.teach(hiramCourses);

    Instructor simon = new Instructor("Simon");
    String simonCourses[] = {"ART001", "ART008", "ART125"};
    simon.teach(simonCourses);

    Instructor viggo = new Instructor("Viggo");
    String viggoCourses[] = {"ART110", "ART124", "ART125"};
    viggo.teach(viggoCourses);

    Instructor ralph = new Instructor("Ralph");
    String ralphCourses[] = {"ART008", "ART064", "ART205", "ART266"};
    ralph.teach(ralphCourses);

    Instructor[] instructors = {john, tom, mary, alicia, betsy, kira, hiram, simon, viggo, ralph};
    String[] courses = {"ART001", "ART002", "ART008", "ART064", "ART110", "ART124", "ART125", "ART201", "ART205", "ART266"};

    Instructor[] coverSet = findMinCover(courses, instructors);
    for (int i=0; i<coverSet.length; ++i)
      System.out.println(coverSet[i]);
  }

  //-----------------------------------------------------------------------

  public static Instructor[] findMinCover(String[] courses, Instructor[] instructors) {
    // implement this
    Instructor[] minCover = {};
    ArrayList<Instructor[]> instructorSubset = new ArrayList<>();
    //boolean array permutations = permute instructors.length
    ArrayList<boolean[]> permutations = permute(instructors.length);
    //iterate through permutations, true value means checking
    for (boolean[] e: permutations) {
      //create a subset array
      Instructor[] subset = new Instructor[e.length];
      //iterate through each e array for true/false values
      for (int i = 0; i < e.length; i++) {
        //if e[i] == true, then add it to Subset
        if (e[i]) subset[i] = instructors[i];
      }
      //add subset to instructorSubset
      instructorSubset.add(subset);
    }
    //iterate through instructorSubset.courses against courses[] for matching values
    for (Instructor[] e: instructorSubset) {
      //init boolean var to confirm if set cover
      boolean setCover = false;
      boolean[] courseCovered = new boolean[courses.length];
      //if the combination of instructors has all the courses in it, then it equals a set cover(setCover = true).
      //Need to handle null values
      for (Instructor b: e) {
        for (int i = 0; i < courses.length; i++) {
          if(b != null) {
            if (b.canTeach(courses[i])) { //(Value 'b' can be null)
              courseCovered[i] = true;
            }
          }
        }
      }
      //check # of instructors
      int numInstruct = 0;
      for (Instructor instructor : e) {
        if (instructor != null) {
          numInstruct++;
        }
      }
      //Check only true values in courseCovered
      int coverCount = 0;
      for (boolean c: courseCovered){
        //init count for checking # of true values
        if (c) coverCount++;
      }
      //compare the count against the length, if all values are true then it is a set cover
      if (courseCovered.length == coverCount) {
        setCover = true;
      }
      //cleaning the set of null values
      Instructor[] covered = new Instructor[numInstruct];
      int i =0;
      for (Instructor b: e) {
        if(b != null) {
          covered[i] = b;
          i++;
        }
      }
      //if minCover.length > instructorSubset[i].length make the new minCover the instructorSubset[i]
      if(setCover && (minCover.length > covered.length || minCover.length == 0)) {
        minCover = covered.clone();
      }
    }
    return minCover;
  }

  //-----------------------------------------------------------------------

  //This function litt *_*
  public static ArrayList<boolean[]> permute(int n) {
    //initialize a new ArrayList<boolean[]> rtnval
    // this will be the return value of the function
    ArrayList<boolean[]> rtnVal = new ArrayList<>(n);
    if (n == 0) {
      // create a new boolean[] array of length = 0
      boolean[] noLen = new boolean[n];
      // add this array to rtnval
      rtnVal.add(noLen);
    } else {
      ArrayList<boolean[]> sublist;
      sublist = permute(n - 1);
      // for each element e of sublist
      for (boolean[] e : sublist) {
        //create a new boolean[] array a1 of length n
        boolean[] a1 = new boolean[n];
        //copy e to a1
        for (int i=0; i<e.length; ++i)
          a1[i] = e[i];
        //set a1[n-1] to true (out of bounds exception of index 0 for length 0 because e doesn't have a value when cloned)
        a1[n-1] = true;
        //add a1 to rtnval
        rtnVal.add(a1);
        //create a new boolean[] array a2 of length n
        boolean[] a2 = new boolean[n];
        //copy e to a2
        for (int i=0; i<e.length; ++i)
          a2[i] = e[i];
        //set a2[n-1] to false
        a2[n-1] = false;
        //add a2 to rtnval
        rtnVal.add(a2);
      }
    }
    return rtnVal;
  }
}
