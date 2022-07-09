package Cs.SpringBoot.AssignMent.mapper;

import Cs.SpringBoot.AssignMent.entity.ClassInfo;
import Cs.SpringBoot.AssignMent.entity.TeacherInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {
//    @Select("select * from class_info where #{id}=teacher_id")
//    public ClassInfo FindClass(int id);
//    @Update("update teacher set username = #{name} where id = #{id}")
//    public int UpdateName(String name,int id);
}
