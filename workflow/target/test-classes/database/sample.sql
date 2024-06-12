insert into Member (memberID, status, firstName, lastName, email, note, created, updated)
values (
    (1, '', 'Stephen', 'Buck', 'stephenbuck@mac.com', '', '', ''),
    (2, '', 'Catherine', 'Buck', 'catherinebuck@mac.com', '', '', '')
);

insert into Skill(skillID, status, name, desc, created, updated)
values (
    (1, '', 'Welding', '', '', ''),
    (2, '', 'Plumbing', '', '', '')

);

insert into MemberSkill(memberSkillID, memberID, skillID, status, note, created, updated)
values (
    (1, 1, 1, '', '', '', ''),
    (2, 2, 2, '', '', '', '')
);

insert into SkillProcess(skillProcessID, skillID, status, name, desc, created, updated)
values (

);

insert into MemberSkillProcess(memberSkillProcessID, memberSkillID, skillProcessID, status, note, created, updated)
values (

);
