<img src="https://i.imgur.com/SqJnBIO.png" title="Clinic Register System" alt="by Arnaldo Fagundes">

</br></br>Código 97% Java (3% SQL) com testes unitários de ações CRUD e não-CRUD para a gestão de uma clínica médica, abarcando consultas, 
cadastro de pacientes, funcionários, médicos e outros.</br></br></br>


<img src="https://i.imgur.com/6JrxQqr.png" title="Let&#39;s start!"/></br></br>
Essas instruções permitirão que você possa obter uma cópia do projeto para alterar/visualizar em sua máquina local para
fins de desenvolvimento e testes.</br></br></br>

<img src="https://i.imgur.com/nwNeYnd.png" title="Recursos utilizados"/></br>
- Java 18</br>
- Spring Boot</br>
- Jakarta Persistence</br>
- PostgreSQL</br>
- Lombok</br>
- ModelMapper</br>
- JUnit 5</br>
- Mockito</br></br></br>

<img src="https://i.imgur.com/mDTxaKb.png"></br></br>
<img src="https://i.imgur.com/14cAmK7.jpg"></br></br>
<a href="https://i.imgur.com/14cAmK7.jpg">Imagem completa</a></br></br></br>


<img src="https://i.imgur.com/hgG53ae.png"></br></br>
<a href="https://documenter.getpostman.com/view/20932178/UzBpLRkn">Clique aqui para acessar a collection no Postman.</a></br></br></br>


<img src="https://i.imgur.com/rYHmz18.png" title="Classes modelo"/></br>
- Médico</br>
- Agenda do médico</br>
- Funcionário</br>
- Paciente</br>
- Plano de saúde do paciente</br>
- Prontuário do paciente</br>
- Paciente infantil</br>
- Telefone</br>
- Endereço</br>
- Consulta</br></br></br>


<img src="https://i.imgur.com/VjCc7p6.png" title="Regras de negócio"/></br>
<h3>Pessoas (médico, paciente e funcionário)</h3>

> - O cadastro, atualização e delete de cada pessoa é feito na sua respectiva classe. Os atributos em comum são herdados da superclasse Person;</br>
> - Ações CRUD do endereço e telefone da pessoa cadastrada é feita exclusivamente no seu respectivo service;</br>
> - Cada médico pode ter apenas uma especialidade, e cada especialidade pode ter apenas um médico;</br>
> - CPF e e-mail são únicos, ou seja, não pode haver outra pessoa com o mesmo valor nestes campos;</br>
> - Todos compartilham a mesma primary key (personId);</br>
> - A única alternativa ao delete é inativar o usuário. Não é possível apagar o registro do banco de dados.

</br><h3>Agenda do médico</h3>
> - Existem horários predefinidos para cada especialidade médica. Por exemplo, no ato de salvar um médico da especialidade Angiologia setará a sua agenda para os horários livres de segunda (08-18h) e sábado (08-12h);</br>
> - Todos os retornos são DTOs.

</br><h3>Telefone e endereço</h3>
> - Só poderão ser adicionados novos caso exista uma pessoa cadastrada no banco de dados (através do personId);</br>
> - Não é possível apagar o registro do banco de dados, apenas inativar.

</br><h3>Consulta</h3>
> - A disponibilidade da consulta exige que a coluna com o horário marcado (agenda do doutor) esteja true, ou seja, disponível para marcação;</br>
> - Caso a coluna esteja false, expandirá um BadRequestException pedindo para marcar em outro horário.


</br></br></br><img src="https://i.imgur.com/SzfgmjV.png" title="Testes unitários"/></br></br>
<img src="https://i.imgur.com/FNWdbHS.png" title="Todos os testes passaram com 100% de métodos e linhas percorridos"/>


</br></br></br><img src="https://i.imgur.com/JamBcYI.png" title="Agradecimentos"/></br>
- Weverton Silva, saudoso amigo que me inspira;</br>
- Gustavo Guanabara, grande professor;
- A minha família, pela compreensão nos momentos de dedicação total ao projeto.