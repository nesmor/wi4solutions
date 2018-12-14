import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Wi4SolutionsSipPeerModule } from './sip-peer/sip-peer.module';
import { Wi4SolutionsDialPlanModule } from './dial-plan/dial-plan.module';
import { Wi4SolutionsGatewayModule } from './gateway/gateway.module';
import { Wi4SolutionsCallDetailRecordModule } from './call-detail-record/call-detail-record.module';
import { Wi4SolutionsCallReportModule } from './call-report/call-report.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Wi4SolutionsSipPeerModule,
        Wi4SolutionsDialPlanModule,
        Wi4SolutionsGatewayModule,
        Wi4SolutionsCallDetailRecordModule,
        Wi4SolutionsCallReportModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsEntityModule {}
