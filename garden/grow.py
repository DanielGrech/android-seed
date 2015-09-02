#!/usr/bin/python

import fileinput
import json
import os
import pystache
import shutil
from dotmap import DotMap

def render_templates(config):
	renderer = pystache.Renderer()

	for root, dirs, files in os.walk(config.output_dir):
		for filename in [f for f in files if f.endswith(('.xml', '.java', '.kt', '.gradle', '.properties'))]:
			fname = os.path.join(root, filename)

			new_contents = str(renderer.render_path(fname, config))

			with open(fname, 'w') as output_file:
				output_file.write(new_contents)


def create_readme(config):
	f = open(os.path.join(config.output_dir, 'README.md'), 'w')
	f.write(config.app_class_prefix + '\n=========')
	f.close()

def rename_files_and_directories(config):
	for root, dirs, files in os.walk(config.output_dir):
		root_prefix = root + "/"
		for d in dirs:
			full_dir_path = root_prefix + d
			if d == 'WATER_ME_WITH_COMPANY':
				os.renames(full_dir_path, root_prefix + config.company_name)

	for root, dirs, files in os.walk(config.output_dir):
		root_prefix = root + "/"
		for d in dirs:
			full_dir_path = root_prefix + d
			if d == 'WATER_ME_WITH_APP_NAME':
				os.renames(full_dir_path, root_prefix + config.app_package_name_prefix)

	for root, dirs, files in os.walk(config.output_dir):
			root_prefix = root + "/"
			for f in files:
				if f.startswith('WATER_ME_WITH_CLASS_PREFIX_'):
					os.renames(root_prefix + f, root_prefix + f.replace('WATER_ME_WITH_CLASS_PREFIX_', config.app_class_prefix))

def copy_template_to_output_dir(root_template_folder, config):
	shutil.copytree(root_template_folder, config.output_dir)

def main():
	config = DotMap(json.loads(''.join(fileinput.input())))

	seed_template_folder = os.path.dirname(os.path.realpath(__file__)) + "/seeds/" + config.language
	
	copy_template_to_output_dir(seed_template_folder, config)
	rename_files_and_directories(config)
	create_readme(config)
	render_templates(config)
	
if __name__ == '__main__':
	main()