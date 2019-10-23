import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectService } from './project.service';
import { PROJECT_ROUTES } from './project.routes';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(PROJECT_ROUTES)
  ],
  declarations: [
    ProjectListComponent,
    ProjectEditComponent
  ],
  providers: [
    ProjectService
  ],
  exports: [
  ]
})
export class ProjectModule { }
