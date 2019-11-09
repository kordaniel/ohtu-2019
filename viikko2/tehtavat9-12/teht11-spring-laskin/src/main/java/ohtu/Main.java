package ohtu;

import ohtu.laskin.Laskin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //new ohtu.laskin.Laskin(new KonsoliIO()).suorita();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //Laskin laskin = ctx.getBean(Laskin.class);
        //laskin.suorita();
        ctx.getBean(Laskin.class).suorita();
    }
}
