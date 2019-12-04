package statistics.matcher;

//import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {
    
    //private int value;
    //private String fieldName;
    private Matcher negation;
    
    
    public HasFewerThan(int value, String category) {
        //this.value = value;
        //this.fieldName = "get" + Character.toUpperCase(category.charAt(0)) + category.substring(1);
        this.negation = new Not(new HasAtLeast(value, category));
    }
    
    @Override
    public boolean matches(Player p) {
        return negation.matches(p);
        /*
        try {
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer) method.invoke(p);
            return playersValue < value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3).toLowerCase());
        }*/
    }
    
}
