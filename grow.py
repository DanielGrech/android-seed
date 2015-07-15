#!/usr/bin/python

import argparse
import sys
import os
import shutil
from os.path import join

root_folder = os.path.dirname(os.path.realpath(__file__)) + "/seed"

class TemplateWriter():
	def __init__(self, output_dir, company_name, app_package_name_prefix, application_id, app_class_prefix, 
		compile_sdk_version, min_sdk_version, target_sdk_version):
		self.output_dir = output_dir
		self.company_name = company_name
		self.app_package_name_prefix = app_package_name_prefix
		self.app_class_prefix = app_class_prefix

		shutil.copytree(root_folder, self.output_dir)

		self.replacements = {
			'{company_name}' : company_name,
			'{app_package_name_prefix}' : app_package_name_prefix,
			'{application_id}' : application_id,
			'{app_class_prefix}' : app_class_prefix,
			'{app_class_prefix_lowercase}' : app_class_prefix[0].lower() + app_class_prefix[1:],
			'{compile_sdk_version}' : compile_sdk_version,
			'{min_sdk_version}' : min_sdk_version,
			'{target_sdk_version}' : target_sdk_version,
		}

	def __rename_folders(self):
		for root, dirs, files in os.walk(self.output_dir):
			for d in dirs:
				root_prefix = root + "/"
				full_dir_path = root_prefix + d
				if d == 'WATER_ME_WITH_COMPANY_GOODNESS':
					os.renames(full_dir_path, root_prefix + self.company_name)

		for root, dirs, files in os.walk(self.output_dir):
			for d in dirs:
				root_prefix = root + "/"
				full_dir_path = root_prefix + d
				if d == 'WATER_ME_WITH_APP_NAME':
					os.renames(full_dir_path, root_prefix + self.app_package_name_prefix)

		for root, dirs, files in os.walk(self.output_dir):
			root_prefix = root + "/"
			for f in files:
				if f.startswith('RENAME_ME_'):
					os.renames(root_prefix + f, root_prefix + f.replace('RENAME_ME_', self.app_class_prefix))

	def __create_readme(self):
		f = open(join(self.output_dir, 'README.md'), 'w')
		f.write(self.app_class_prefix + '\n=========')
		f.close()

	def __run_replacement(self):
		for root, dirs, files in os.walk(self.output_dir):
			for filename in files:
				fname = join(root, filename)
				contents = open(fname).read()
				out = open(fname, 'w')
				for i in self.replacements.keys():
					contents = contents.replace(i, str(self.replacements[i]))
				out.write(contents)
				out.close()

	def create(self):
		self.__rename_folders()
		self.__create_readme()
		self.__run_replacement()


def main():
	parser = argparse.ArgumentParser(description='Grow a nice Android app from this beautiful seed')
	parser.add_argument('company_name', metavar='COMPANY_NAME', help='the name of your company. Used in the class package names')
	parser.add_argument('app_package_name_prefix', metavar='PACAKGE_PREFIX', help='The name of the application. Used in the class package names')
	parser.add_argument('application_id', metavar='APPLICATION_ID', help='the name of the generated package')
	parser.add_argument('app_class_prefix', metavar='PREFIX', help="prefix for import classes. Eg 'Weather' for WeatherApp and WeatherContentProvider")
	parser.add_argument('compile_sdk_version', metavar='COMPILE_SDK_VERSION', type=int, help="Sdk version to use to compile the app. Eg 19")
	parser.add_argument('min_sdk_version', metavar='MIN_SDK_VERSION', type=int, help="Minimum sdk version the app targets. Eg 14")
	parser.add_argument('target_sdk_version', metavar='TARGET_SDK_VERSION', type=int, help="Target sdk version the app targets. Eg 19")
	parser.add_argument('--output', metavar='dir', help='Output directory for the new app. Default to current directory', default='.')

	args = parser.parse_args()

	template = TemplateWriter(args.output, args.company_name, args.app_package_name_prefix, args.application_id, args.app_class_prefix, 
		args.compile_sdk_version, args.min_sdk_version, args.target_sdk_version)
	template.create()
	
if __name__ == '__main__':
	main()