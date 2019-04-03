package com.easywork.easywork.springboot.quartz;

import com.easywork.easywork.springboot.quartz.model.Step;
import com.easywork.easywork.springboot.quartz.model.Task;
import com.easywork.easywork.springboot.quartz.steps.TestStep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyworkSpringbootQuartzApplicationTests {

    @Test
    public void contextLoads() throws JAXBException, IOException {
        Task task = new Task();
        Step step = new Step().setId("step1").setDesc("步骤1").setProcessor(TestStep.class);
        Step step2 = new Step().setId("step2").setDesc("步骤2").setProcessor(TestStep.class);
        LinkedList<Step> steps = new LinkedList<>();
        steps.add(step);
        steps.add(step2);
        task.setId("1").setName("hello").setDesc("desc").setTaskType(Task.Type.WRITE).setSteps(steps);
        JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
        FileOutputStream fileOutputStream = new FileOutputStream("classPath:a.xml");
        marshaller.marshal(task, fileOutputStream);
        fileOutputStream.close();

        System.out.println();
    }

}
