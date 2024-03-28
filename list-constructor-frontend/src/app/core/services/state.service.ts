import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ConstructedList } from '../models/ListModels';

export enum ViewState {
  Loading,
  Home,
  ViewingList,
  CreatingList,
  SavingList,
  CreatingListItems,
  SavingListItems
}

@Injectable({
  providedIn: 'root'
})
export class StateService {
  private currentState = new BehaviorSubject<ViewState>(ViewState.Loading);
  private currentList = new BehaviorSubject<ConstructedList | null>(null);
  private index = 0;

  constructor() {}

  public listenToCurrentState(): Observable<ViewState> {
    return this.currentState.asObservable();
  }

  public listenToCurrentList(): Observable<ConstructedList | null> {
    return this.currentList.asObservable();
  }

  public getIndex(): number {
    return this.index;
  }

  public updateState(state: ViewState) {
    this.currentState.next(state);
  }

  public updateCurrentList(list: ConstructedList | null) {
    this.currentList.next(list);
  }

  public setIndex(index: number) {
    this.index = index;
  }
}
