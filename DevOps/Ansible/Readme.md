# Ansible

One of Ansible’s greatest strengths is its ability to run regular shell commands verbatim, so you can take existing scripts and commands and work on converting them into idempotent playbooks as time allows.

No extra software to be installed on servers.

Idempotence: ability to perform the same action regardless of the server it is run on.

Inventory files: Ansible uses basic inventory file to communicate with your servers. Like a hosts file (at /etc/hosts) that matches IP addresses to domain names, an Ansible inventory file matches servers (IP addresses or domain names) to groups.

**Default location for ansible inventory file** `/etc/ansible/hosts`

```
[example]
www.example.com
```

example is a group of servers you are managing and www.example.com is the domain name (IP address) of a server in that group. (Ansible defaults to port 22. You need to specify if you are using something else)

playbook:  It's essentially a set of instructions that you send to a single or group of servers to perform system updates, install software, or any other complex task. Playbooks are composed of one or more 'plays' which target a specific set of hosts, defining the tasks to be executed on those hosts.

Ansible allows admins to run ad-hoc commands on hundreds of machines at the same time using the ansible command.

Ansible runs all commands in parallel on all hosts. (use `-f 1` to tell ansible to use only one fork -> no parallelism) Use -f fork value depending on your network capacity.

## Ansible ad-hoc

To run an adhoc command on all hosts, use `ansible <groupname> -a "df -h"` --> Runs the df command on all hosts in groupname (-a for arguments)

the `become: true` command tells ansible to run as root user (with sudo).

running the same configuration again on the servers would do nothing, and ansible would return "changed: false"

`--limit` flag is used to run a command against a specific instance in a group

In ansible you cannot run watch polling command like `tail -f` as ansible only displays output when the command is complete

## Ansible playbooks

Config file with a list of tasks. Written in yaml. 

`ansible-playbook playbook.yml`

**Options**

pass variables with `-e "key=value, key=value"`
pass inventory file `-i`

You can run tasks selectively with the help of tags.
- `--tags`
- `--skip-tags`

You can import tasks from other playbooks.
- `import_tasks: foo.yml`
  `tags: foo`
Give it a tag to execute selectively.

if you want to dynamically import tasks, use `include_tasks` instead

Blocks can be used to group related tasks.
`blocks` + `rescue` + `always` -> act like try catch finally

Ansible set_fact is used to dynamically update variables while register is used only to store data in a variable.

Roles provide an easy way to organize ansible infrastructure. Ansible looks for main.yml files inside tasks in the roles directory.

Handlers are used to handle events --> called via `notify` option