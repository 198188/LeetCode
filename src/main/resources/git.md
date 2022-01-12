###配置本地分支对应到远程分支
创建分支并切换分支，同时对应到远程分支
```gitexclude
git checkout -b process origin/process
```
如果已有本地分支则可以使用git checkout devbranck切换到本地分支，通过 `git branch --set-upstream` 命令设置关联
```gitexclude
git branch --set-upstream process origin/process
```
###拉取远程分支
`git pull`或者`git fetch origin`可以拉取所有远程分支

使用`git pull origin devbranch`命令可以拉取单远程分支
####推送到远程分支
使用命令`git push origin devbranch`推送分支到远程仓库