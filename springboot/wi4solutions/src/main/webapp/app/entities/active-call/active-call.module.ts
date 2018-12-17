import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    ActiveCallComponent,
    ActiveCallDetailComponent,
    ActiveCallUpdateComponent,
    ActiveCallDeletePopupComponent,
    ActiveCallDeleteDialogComponent,
    activeCallRoute,
    activeCallPopupRoute
} from './';

const ENTITY_STATES = [...activeCallRoute, ...activeCallPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ActiveCallComponent,
        ActiveCallDetailComponent,
        ActiveCallUpdateComponent,
        ActiveCallDeleteDialogComponent,
        ActiveCallDeletePopupComponent
    ],
    entryComponents: [ActiveCallComponent, ActiveCallUpdateComponent, ActiveCallDeleteDialogComponent, ActiveCallDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsActiveCallModule {}
