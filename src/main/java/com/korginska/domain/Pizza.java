package com.korginska.domain;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sofia on 18.12.2017.
 */
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDNumber", nullable = false)
    private Long id;
    @Column(name = "PizzaName", nullable = false, length = 45)
    private String pizzaName;
    @Column(name = "Size", nullable = false, length = 45)
    private Integer size;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @ManyToMany(mappedBy = "pizzas")
    private Set<ChiefCook> chiefs;

    Pizza(){}
    Pizza(String pizzaName,Integer size,Integer amount){
        this.pizzaName=pizzaName;
        this.size=size;
        this.amount=amount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long idPizza) {
        this.id = idPizza;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<ChiefCook> getChiefs() {
        return chiefs;
    }

    public void setChiefs(Set<ChiefCook> chiefs) {
        this.chiefs = chiefs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (id != null ? !id.equals(pizza.id) : pizza.id != null) return false;
        if (pizzaName != null ? !pizzaName.equals(pizza.pizzaName) : pizza.pizzaName != null) return false;
        if (size != null ? !size.equals(pizza.size) : pizza.size != null) return false;
        if (amount != null ? !amount.equals(pizza.amount) : pizza.amount != null) return false;
        return chiefs != null ? chiefs.equals(pizza.chiefs) : pizza.chiefs == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pizzaName != null ? pizzaName.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (chiefs != null ? chiefs.hashCode() : 0);
        return result;
    }
}
