package com.kgc.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들(createdDate, modifiedDate) 도 칼럼으로 인식하도록 해준다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing(Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능) 기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate //Entity 가 생성될때의 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할때의 시간 자동저장
    private LocalDateTime modifiedDate;
}
