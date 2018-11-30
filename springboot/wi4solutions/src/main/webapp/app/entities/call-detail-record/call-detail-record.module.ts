import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    CallDetailRecordComponent,
    CallDetailRecordDetailComponent,
    CallDetailRecordUpdateComponent,
    CallDetailRecordDeletePopupComponent,
    CallDetailRecordDeleteDialogComponent,
    callDetailRecordRoute,
    callDetailRecordPopupRoute
} from './';

const ENTITY_STATES = [...callDetailRecordRoute, ...callDetailRecordPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CallDetailRecordComponent,
        CallDetailRecordDetailComponent,
        CallDetailRecordUpdateComponent,
        CallDetailRecordDeleteDialogComponent,
        CallDetailRecordDeletePopupComponent
    ],
    entryComponents: [
        CallDetailRecordComponent,
        CallDetailRecordUpdateComponent,
        CallDetailRecordDeleteDialogComponent,
        CallDetailRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsCallDetailRecordModule {}
