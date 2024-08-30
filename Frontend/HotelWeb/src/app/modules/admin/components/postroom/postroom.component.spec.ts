import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostroomComponent } from './postroom.component';

describe('PostroomComponent', () => {
  let component: PostroomComponent;
  let fixture: ComponentFixture<PostroomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostroomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
