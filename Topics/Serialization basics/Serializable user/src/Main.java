import java.io.Serializable;

class User {
    String name;
    private transient String password;

    private static final long serialVersionUID=1L;
}