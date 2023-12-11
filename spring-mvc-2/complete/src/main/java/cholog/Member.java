package cholog;

public class Member {
    private Long id;
    private String name;
    private int age;

    public Member() {
    }

    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static Member toEntity(Member member, Long id) {
        return new Member(id, member.name, member.age);
    }

    public void update(Member newMember) {
        this.name = newMember.name;
        this.age = newMember.age;
    }
}
