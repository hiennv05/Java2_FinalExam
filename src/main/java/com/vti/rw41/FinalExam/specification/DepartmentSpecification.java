package com.vti.rw41.FinalExam.specification;

import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DepartmentSpecification {
    public static Specification<Department> buildWhere(String search, DepartmentFilterForm filterForm) {
        if(StringUtils.isEmpty(search)) {
            return null;
        }
        search = search.trim();
        CustomSpecification createDate = new CustomSpecification("createDate", search);
        CustomSpecification minDate = new CustomSpecification("minDate", search);
        CustomSpecification maxDate = new CustomSpecification("maxDate", search);

        return Specification.where(createDate).or(minDate).or(maxDate);
    }
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
            if(field.equalsIgnoreCase("createDate")) {
                return criteriaBuilder.equal(root.get("create_date").as(java.sql.Date.class),
                        (Date) value);
            }
            if(field.equalsIgnoreCase("minDate")) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("create_date").as(java.sql.Date.class),
                        (Date) value);
            }
            if(field.equalsIgnoreCase("maxDate")) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("create_date").as(java.sql.Date.class),
                        (Date) value);
            }

            return null;
        }
    }

}
