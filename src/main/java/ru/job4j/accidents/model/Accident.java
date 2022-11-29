package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accident")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private AccidentType type;
    @ManyToMany
    @JoinTable(name = "accident_rule",
                joinColumns = {@JoinColumn(name = "accident_id")},
                inverseJoinColumns = {@JoinColumn(name = "rule_id")})
    private Set<Rule> rules = new HashSet<>();
    private String name;
    private String text;
    private String address;
}