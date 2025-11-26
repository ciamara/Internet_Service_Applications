package labs.aui;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "simple_owners")
public class SimpleOwner implements Comparable<SimpleOwner>, Serializable {
    @Id
    @Column(name = "simple_owner_id", nullable = false)
    private UUID simpleOwnerId;

    @Column(name = "simple_owner_name")
    private String simpleOwnerName;

    @Override
    public int compareTo(SimpleOwner other) {
        return this.simpleOwnerName.compareTo(other.simpleOwnerName);
    }
}
