package com.devsurfer.domain.enums

enum class BranchState(val value: Int) {
    NONE(0),
    COMMIT(1),
    MERGE(2)
}