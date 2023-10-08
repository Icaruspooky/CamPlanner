package com.seals.camplanner.event.models;

import java.sql.Timestamp;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import com.seals.camplanner.commons.models.BaseEntity;
import com.seals.camplanner.location.models.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "events")
public class Event extends BaseEntity {
    @Column(name = "private_event", nullable = false)
    private boolean privateEvent;

    @Column(name = "event_date", nullable = false)
    private Timestamp eventDate;

    @Column(name = "starts", nullable = false)
    private Timestamp starts;

    @Column(name = "ends", nullable = false)
    private Timestamp ends;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                                   ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                                   : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                                              .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        Event event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

