import com.dianaszanto.jobsearchapi.controller.ClientControllerUnitTest;
import com.dianaszanto.jobsearchapi.controller.JobControllerUnitTest;
import com.dianaszanto.jobsearchapi.service.impl.JobServiceImplTest;
import com.dianaszanto.jobsearchapi.unit.ClientServiceUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientServiceUnitTest.class, JobServiceImplTest.class, ClientControllerUnitTest.class,
JobControllerUnitTest.class})
public class SpringWebappApplicationTests {

    @Test
    public void contextLoads() {
    }
}