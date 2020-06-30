package spring.model;

public class Author {

    private long id;
    private String firstname;
    private String lastname;

    public Author( String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
               "id=" + id +
               ", firstname='" + firstname + '\'' +
               ", lastname='" + lastname + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Author author = (Author) o;

        if (id != author.id) { return false; }
        if (firstname != null ? !firstname.equals(author.firstname) : author.firstname != null) { return false; }
        return lastname != null ? lastname.equals(author.lastname) : author.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
