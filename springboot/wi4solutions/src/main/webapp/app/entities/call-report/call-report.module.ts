import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    CallReportComponent,
    CallReportDetailComponent,
    CallReportUpdateComponent,
    CallReportDeletePopupComponent,
    CallReportDeleteDialogComponent,
    callReportRoute,
    callReportPopupRoute
} from './';

const ENTITY_STATES = [...callReportRoute, ...callReportPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CallReportComponent,
        CallReportDetailComponent,
        CallReportUpdateComponent,
        CallReportDeleteDialogComponent,
        CallReportDeletePopupComponent
    ],
    entryComponents: [CallReportComponent, CallReportUpdateComponent, CallReportDeleteDialogComponent, CallReportDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsCallReportModule {}
