import { UUID } from "crypto";

export interface BaseEntity {
    id?: UUID,
    name: string,
    description?: string;
}

export interface ConstructedList extends BaseEntity {
    type: ConstructedListType,
    itemIds?: UUID[];
}

export enum ConstructedListType {
    CHECK,
    DETAIL,
    RANK,
}

export interface ListOrder {
    listId: UUID,
    index: number
}
