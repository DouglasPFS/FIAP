import { TestBed } from '@angular/core/testing';

import { CadastrarAvaliacaoService } from './cadastrar-avaliacao.service';

describe('CadastrarAvaliacaoService', () => {
  let service: CadastrarAvaliacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadastrarAvaliacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
