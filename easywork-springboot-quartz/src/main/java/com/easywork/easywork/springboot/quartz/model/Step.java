package com.easywork.easywork.springboot.quartz.model;

import com.easywork.easywork.springboot.quartz.task.IStepHandler;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 03:57
 * Description:
 */
@Data
@ToString
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Step {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String desc;

    @XmlAttribute
    private Class<? extends IStepHandler> processor;
}
