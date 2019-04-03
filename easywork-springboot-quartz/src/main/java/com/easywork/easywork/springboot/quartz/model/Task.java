package com.easywork.easywork.springboot.quartz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 03:45
 * Description:
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    public enum Type {
        DEFAULT, RESTFUL, READ, WRITE
    }

    private String id;

    private String name;

    private String desc;

    private Type taskType = Type.DEFAULT;

    @XmlElementWrapper(name = "steps")
    @XmlElement(name = "step")
    private List<Step> steps;
}
