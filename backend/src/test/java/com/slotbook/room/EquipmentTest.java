package com.slotbook.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class EquipmentTest {

    private static Equipment equipmentWithId(String name, Long id) {
        Equipment equipment = new Equipment(name);
        // Simulates what Hibernate does after an INSERT
        ReflectionTestUtils.setField(equipment, "id", id);
        return equipment;
    }

    @Test
    void equipmentsWithSameIdAreEqual() {
        Equipment first = equipmentWithId("Projector", 1L);
        Equipment second = equipmentWithId("Projector", 1L);

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @Test
    void equipmentsWithDifferentIdsAreNotEqual() {
        Equipment projector = equipmentWithId("Projector", 1L);
        Equipment whiteboard = equipmentWithId("Whiteboard", 2L);

        assertThat(projector).isNotEqualTo(whiteboard);
    }

    @Test
    void twoNewEquipmentsAreNotEqual() {
        Equipment projector = new Equipment("Projector");
        Equipment whiteboard = new Equipment("Whiteboard");

        assertThat(projector).isNotEqualTo(whiteboard);
    }

    @Test
    void equipmentIsStillFoundInHashSetAfterReceivingAnId() {
        Equipment projector = new Equipment("Projector");
        Set<Equipment> equipments = new HashSet<>();
        equipments.add(projector);

        ReflectionTestUtils.setField(projector, "id", 1L);

        assertThat(equipments).contains(projector);
    }

    @Test
    void blankNameIsRejected() {
        assertThatThrownBy(() -> new Equipment(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
