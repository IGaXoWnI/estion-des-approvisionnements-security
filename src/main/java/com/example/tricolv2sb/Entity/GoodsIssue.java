package com.example.tricolv2sb.Entity;

import com.example.tricolv2sb.Entity.Enum.GoodsIssueMotif;
import com.example.tricolv2sb.Entity.Enum.GoodsIssueStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "goods_issues")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"issueLines"})
public class GoodsIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String issueNumber;
    
    @Column(nullable = false)
    private LocalDate issueDate;
    
    @Column(nullable = false)
    private String destination;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsIssueMotif motif;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsIssueStatus status;
    
    @OneToMany(mappedBy = "goodsIssue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<GoodsIssueLine> issueLines = new HashSet<>();
}