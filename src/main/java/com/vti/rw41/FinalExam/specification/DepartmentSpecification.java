package com.vti.rw41.FinalExam.specification;

import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DepartmentSpecification {

    @SuppressWarnings("depreation")
    public static Specification<Department> buildWhere(String search, DepartmentFilterForm filterForm) {
        Specification<Department> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification name = new CustomSpecification("name", search);

           where = Specification.where(name);
        }
        if(filterForm!= null && filterForm.getCreateDate() != null) {
            CustomSpecification createDate = new CustomSpecification("createDate", filterForm.getCreateDate());
            if(where == null) {
                where = createDate;
            }else
                where = where.and(createDate);
        }
        if(filterForm!= null && filterForm.getCreateDate() != null) {
            CustomSpecification minDate = new CustomSpecification("minDate", filterForm.getCreateDate());
            if(where == null) {
                where = minDate;
            }else
                where = where.and(minDate);
        }
        if(filterForm!= null && filterForm.getCreateDate() != null) {
            CustomSpecification maxDate = new CustomSpecification("maxDate", filterForm.getCreateDate());
            if(where == null) {
                where = maxDate;
            }else
                where = where.and(maxDate);
        }
        return where;
    }
        @SuppressWarnings("serial")
        @RequiredArgsConstructor
       static class CustomSpecification implements Specification<Department> {
            @NonNull
            private String field;

            @NonNull
            private Object value;


            @Override
            public Predicate toPredicate(Root<Department> root,
                                         CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
                if(field.equalsIgnoreCase("name")) {
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
                }
                if (field.equalsIgnoreCase("createDate")) {
                    return criteriaBuilder.equal(root.get("create_date").as(java.sql.Date.class),
                            (Date) value);
                }
                if (field.equalsIgnoreCase("minDate")) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("create_date").as(java.sql.Date.class),
                            (Date) value);
                }
                if (field.equalsIgnoreCase("maxDate")) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("create_date").as(java.sql.Date.class),
                            (Date) value);
                }

            return null;
        }
    }

}
