package com.digitalgd.cloud.code.generator.catalog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;


/**
 * <p>
 * 
 * @since 2021-07-05
 * </p>
 *
 * @author songshuai3223@163.com
 */
@TableName("user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
private static final long serialVersionUID=1L;


    private Integer id;
        private String name;
        private Integer age;
        private String email;
}
