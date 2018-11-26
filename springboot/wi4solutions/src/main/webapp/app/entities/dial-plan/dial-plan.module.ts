import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    DialPlanComponent,
    DialPlanDetailComponent,
    DialPlanUpdateComponent,
    DialPlanDeletePopupComponent,
    DialPlanDeleteDialogComponent,
    dialPlanRoute,
    dialPlanPopupRoute
} from './';

const ENTITY_STATES = [...dialPlanRoute, ...dialPlanPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DialPlanComponent,
        DialPlanDetailComponent,
        DialPlanUpdateComponent,
        DialPlanDeleteDialogComponent,
        DialPlanDeletePopupComponent
    ],
    entryComponents: [DialPlanComponent, DialPlanUpdateComponent, DialPlanDeleteDialogComponent, DialPlanDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsDialPlanModule {}
