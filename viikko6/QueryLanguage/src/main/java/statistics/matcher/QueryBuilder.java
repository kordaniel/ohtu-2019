package statistics.matcher;

public class QueryBuilder {

    private Matcher matcher;
    
    public QueryBuilder() {
        initializeMatcher();
    }
    
    public Matcher build() {
        Matcher completedMatcher = this.matcher;
        initializeMatcher();
        return completedMatcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(this.matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
    
    private void initializeMatcher() {
        this.matcher = new All();
    }
    
}
