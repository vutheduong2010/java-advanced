import entity.Department;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buildSessionFactory()) {
           factory.inTransaction(session -> {
               var department = new Department();
               department.setName("Giám đốc");
               session.persist(department);
           });
            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Bảo vệ");
                session.persist(department);
            });
            factory.inSession(session -> {
                // hibernate query language
                var hql = "FROM Department";
                var departments = session
                    .createSelectionQuery(hql, Department.class)
                    .getResultList();
                for (var department : departments) {
                    System.out.println("Department = " + department);
                }
            });

            factory.inSession(session -> {
                var deparment = session.get(Department.class, 1);
                System.out.println("deparment = " + deparment);
            });

            factory.inSession(session -> {
                var hql = "FROM Department WHERE name = :name";
                var deparment = session
                        .createQuery(hql, Department.class)
                        .setParameter("name", "Bảo vệ")
                        .uniqueResult();
                System.out.println("deparment = " + deparment);
            });

            factory.inTransaction(session -> {
                var department = session.get(Department.class, 2);
                department.setName("Kinh doanh");
                session.merge(department);
            });

            factory.inTransaction(session -> {
                var department = session.get(Department.class, 1);
                session.remove(department);
            });
        }
    }
}
