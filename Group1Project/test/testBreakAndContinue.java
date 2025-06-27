public class testBreakAndContinue {
    
     public void breakandcontinue() {
        for(int i = 0; i < 100; i++) {
            if(i == 74) break;
            if(i % 9 != 0) continue;
            System.out.println(i);
        }
    }
}
