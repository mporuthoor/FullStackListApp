import { UUID } from 'crypto';
import { BaseEntity } from './ListModels';

export interface ListItemEntity extends BaseEntity {
  listId: UUID;
}

export interface CheckListItem extends ListItemEntity {
  checked: boolean;
}

export interface DetailListItem extends ListItemEntity {
  details: string[];
}

export interface RankListItem extends ListItemEntity {
  rank: number;
}
