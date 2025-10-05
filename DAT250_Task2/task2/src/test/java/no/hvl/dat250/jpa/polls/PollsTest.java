package no.hvl.dat250.jpa.polls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ast.task2.domain.Poll;
import com.ast.task2.domain.User;
import com.ast.task2.domain.Vote;
import com.ast.task2.domain.VoteOption;



public class PollsTest {

    private EntityManagerFactory emf;


    private void populate(EntityManager em) {
        User alice = new User("alice", "alice@online.com");
        User bob = new User("bob", "bob@bob.home");
        User eve = new User("eve", "eve@mail.org");
        em.persist(alice);
        em.persist(bob);
        em.persist(eve);
        Poll poll = alice.createPoll("Vim or Emacs?");
        VoteOption vim = poll.addVoteOption("Vim");
        VoteOption emacs = poll.addVoteOption("Emacs");
        Poll poll2 = eve.createPoll("Pineapple on Pizza");
        VoteOption yes = poll2.addVoteOption("Yes! Yammy!");
        VoteOption no = poll2.addVoteOption("Mamma mia: Nooooo!");
        em.persist(poll);
        em.persist(poll2);
        em.persist(alice.voteFor(vim));
        em.persist(bob.voteFor(vim));
        em.persist(eve.voteFor(emacs));
        em.persist(eve.voteFor(yes));
    }

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = new PersistenceConfiguration("polls")
                .managedClass(Poll.class)
                .managedClass(User.class)
                .managedClass(Vote.class)
                .managedClass(VoteOption.class)
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:polls")
                .property(PersistenceConfiguration.SCHEMAGEN_DATABASE_ACTION, "drop-and-create")
                .property(PersistenceConfiguration.JDBC_USER, "sa")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                .createEntityManagerFactory();
        emf.runInTransaction(em -> {
            populate(em);

         
        // List<Object[]> tables = em.createNativeQuery("SHOW TABLES").getResultList();
        // System.out.println("=== H2 Tabellen ===");
        // tables.forEach(System.out::println);
        });
        this.emf = emf;
    }

    @Test
    public void testUsers() {
        emf.runInTransaction(em -> {
            Integer actual = (Integer) em.createNativeQuery("select count(id) from users", Integer.class).getSingleResult();
            assertEquals(3, actual);

            User maybeBob = em.createQuery("select u from User u where u.username like 'bob'", User.class).getSingleResultOrNull();
            assertNotNull(maybeBob);
        });
    }

    @Test
    public void testVotes() {
        emf.runInTransaction(em -> {
            Long vimVotes = em.createQuery("select count(v) from Vote v join v.votesOn as o join o.poll as p join p.createdBy u where u.email = :mail and o.presentationOrder = :order", Long.class).setParameter("mail", "alice@online.com").setParameter("order", 0).getSingleResult();
            Long emacsVotes = em.createQuery("select count(v) from Vote v join v.votesOn as o join o.poll as p join p.createdBy u where u.email = :mail and o.presentationOrder = :order", Long.class).setParameter("mail", "alice@online.com").setParameter("order", 1).getSingleResult();
            assertEquals(2, vimVotes);
            assertEquals(1, emacsVotes);

        });
    }

    @Test
    public void testOptions() {
        emf.runInTransaction(em -> {
            List<String> poll2Options = em.createQuery("select o.caption from Poll p join p.options o join p.createdBy u where u.email = :mail order by o.presentationOrder", String.class).setParameter("mail", "eve@mail.org").getResultList();
            List<String> expected = Arrays.asList("Yes! Yammy!", "Mamma mia: Nooooo!");
            assertEquals(expected, poll2Options);
        });
    }


    @Test
    public void printAllTablesAndContents() {
        emf.runInTransaction(em -> {
            System.out.println("=== ALL H2 TABLES ===");

            List<String> tableNames = em.createNativeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'"
            ).getResultList();

            for (String tableName : tableNames) {
                System.out.println("\n--- Table: " + tableName + " ---");

                List<String> columns = em.createNativeQuery(
                    "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = :table"
                ).setParameter("table", tableName).getResultList();

                System.out.print("Columns: ");
                columns.forEach(col -> System.out.print(col + " "));
                System.out.println();

                List<Object[]> rows = em.createNativeQuery("SELECT * FROM " + tableName).getResultList();
                for (Object[] row : rows) {
                    System.out.println(Arrays.toString(row));
                }
            }
        });
    }



    
}

