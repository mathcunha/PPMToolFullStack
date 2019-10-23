import { Routes } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectEditComponent } from './project-edit/project-edit.component';

export const PROJECT_ROUTES: Routes = [
  {
    path: 'project',
    component: ProjectListComponent
  },
  {
    path: 'project/:id',
    component: ProjectEditComponent
  }
]
